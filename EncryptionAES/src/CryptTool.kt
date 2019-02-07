import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object CryptTool {

    fun generateKey(): SecretKey {

        var secRand = SecureRandom()
        var key = KeyGenerator.getInstance("AES")
        key.init(128, secRand)

        return key.generateKey()
    }

    fun createIV(): ByteArray {

        var vec = ByteArray(16)
        var secureRandom = SecureRandom()
        secureRandom.nextBytes(vec)

        return vec
    }

    fun doEncryption(text: String, secretKey: SecretKey, initVec: ByteArray): ByteArray {

        var cipherAES = Cipher.getInstance("AES/CBC/PKCS5Padding")
        var vec = IvParameterSpec(initVec)
        cipherAES.init(Cipher.ENCRYPT_MODE, secretKey, vec)

        return cipherAES.doFinal(text.toByteArray())
    }

    fun doDecryption(cipherText: ByteArray, secretKey: SecretKey, initVec: ByteArray): String {

        var cipherAES = Cipher.getInstance("AES/CBC/PKCS5Padding")
        var vec = IvParameterSpec(initVec)
        cipherAES.init(Cipher.DECRYPT_MODE, secretKey, vec)
        var textBytes: ByteArray = cipherAES.doFinal(cipherText)

        return String(textBytes)

    }

    fun binaryToHex(bytesArray: ByteArray): String {

        val result = StringBuilder(bytesArray.size * 2)

        for (byte in bytesArray) {
            val newByte = String.format("%x", byte)
            result.append(newByte)
        }

        return result.toString()
    }
}