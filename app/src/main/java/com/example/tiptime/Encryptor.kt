package com.example.tiptime

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

private fun myCipher(string: String, digits: Int): String {
    val code = string.split("").toMutableList()
    code.removeFirst()
    code.removeLast()
    val characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%&'()*+,-./:;<=>?@[\\]^_{}"
    var index = 0
    for (i in string) {
        if (i in characters) {
            code[index] = characters[(characters.indexOf(i) + digits) % 90].toString()
            index += 1
        } else {
            code[index] = i.toString()
            index += 1
        }
    }
    return code.joinToString("")
}
@RequiresApi(Build.VERSION_CODES.O)
private fun encoding(input: String, someNumber: Int): String {
    val someNumber2 = someNumber % 90
    val otherNumber = (someNumber2 * 5) % 90
    val encoder = Base64.getEncoder()
    val byteY = myCipher(input, someNumber2).toByteArray()
    val encodedString = encoder.encodeToString(byteY)
    return myCipher(encodedString, (otherNumber * 5) % 90)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun decoding(code: String, someNumber: Int): String {
    val someNumber2 = someNumber % 90
    val otherNumber = (someNumber2 * 5) % 90
    val decoder = Base64.getDecoder()
    val encodedString = myCipher(code, 90 - ((otherNumber * 5) % 90))
    val byteY = decoder.decode(encodedString)
    return myCipher(String(byteY), 90 - someNumber2)
}
@RequiresApi(Build.VERSION_CODES.O)
fun fullEncode(text: String, someNumber: Int): String {
    val codeList = text.split(" ")
    val output = mutableListOf<String>()
    for (i in codeList) {
        output.add(encoding(i, someNumber))
    }
    return output.joinToString(" ")
}
@RequiresApi(Build.VERSION_CODES.O)
fun fullDecode(code: String, someNumber: Int): String {
    val textList = code.split(" ")
    val output = mutableListOf<String>()
    for (i in textList) {
        output.add(decoding(i, someNumber))
    }
    return output.joinToString(" ")
}
