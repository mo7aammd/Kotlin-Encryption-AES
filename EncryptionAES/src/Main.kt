fun main(args: Array<String>) {

    val key = CryptTool.generateKey()
    val initVec = CryptTool.createIV()
    val plainText = "السلام عليكم " // text to be encrypted

    val cipherText = CryptTool.doEncryption(plainText,key,initVec)

    val decryption = CryptTool.doDecryption(cipherText,key,initVec)

    println("text : $plainText")
    println("key : ${CryptTool.binaryToHex(key.encoded)}")

    println("text after Encryption : ${String(cipherText)}")
    println("text after Decryption : $decryption")

}