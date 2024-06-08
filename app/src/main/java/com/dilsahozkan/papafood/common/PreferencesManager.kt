package com.dilsahozkan.papafood.common

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class PreferencesManager(private val context: Context) {

    private val API_KEY = stringPreferencesKey("api_key")
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "api_prefs")

    fun storeApiKey(apiKey: String) {
        val encodedApiKey = encryptData(apiKey)
        runBlocking {
            context.dataStore.edit { settings ->
                settings[API_KEY] = encodedApiKey
            }
        }
    }

    fun getApiKey(): String {
        return runBlocking {
            val apiKeyFlow = context.dataStore.data.map { preferences ->
                preferences[API_KEY] ?: ""
            }
            val encodedApiKey = apiKeyFlow.first()
            decryptData(encodedApiKey)
        }
    }

    private fun encryptData(data: String): String {
        val key: SecretKey = generateKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val ivBytes = cipher.iv
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        val ivBase64 = android.util.Base64.encodeToString(ivBytes, android.util.Base64.DEFAULT)
        val dataBase64 = android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
        return "$ivBase64:$dataBase64"
    }

    private fun decryptData(data: String): String {
        val parts = data.split(":").toTypedArray()
        val ivBytes = android.util.Base64.decode(parts[0], android.util.Base64.DEFAULT)
        val encryptedBytes = android.util.Base64.decode(parts[1], android.util.Base64.DEFAULT)
        val key = getKey()

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, ivBytes)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)

        return String(cipher.doFinal(encryptedBytes))
    }

    private fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore"
        )
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            "alias",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            build()
        }
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    private fun getKey(): SecretKey {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        return keyStore.getKey("alias", null) as SecretKey
    }
}
