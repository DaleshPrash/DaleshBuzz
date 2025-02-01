package com.example.daleshbuzz.encyption
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object AESEncryptionUtil {

    private const val AES_ALGORITHM = "AES"
    private const val AES_TRANSFORMATION = "AES/GCM/NoPadding"
    private const val GCM_IV_LENGTH = 12 // Recommended IV length for GCM

    fun generateAESKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM)
        keyGenerator.init(256) // AES 256-bit key
        return keyGenerator.generateKey()
    }

    fun encryptPassword(password: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        val iv = ByteArray(GCM_IV_LENGTH)
        SecureRandom().nextBytes(iv) // Generate random IV

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        val encryptedBytes = cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8))

        return Base64.encodeToString(iv + encryptedBytes, Base64.NO_WRAP)
    }

    fun decryptPassword(encryptedPassword: String, secretKey: SecretKey): String {
        val decodedBytes = Base64.decode(encryptedPassword, Base64.NO_WRAP)
        val iv = decodedBytes.copyOfRange(0, GCM_IV_LENGTH)
        val encryptedBytes = decodedBytes.copyOfRange(GCM_IV_LENGTH, decodedBytes.size)

        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        return String(decryptedBytes, StandardCharsets.UTF_8)
    }
}

fun encryptUserPasswordForService(password: String): String {
    val secretKey = AESEncryptionUtil.generateAESKey()
    return AESEncryptionUtil.encryptPassword(password, secretKey)
}