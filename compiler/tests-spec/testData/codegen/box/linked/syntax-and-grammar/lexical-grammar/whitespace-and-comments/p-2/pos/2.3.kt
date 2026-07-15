// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: CR character at beginning and end of string
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str1 = "\u000Dhello"
    val str2 = "hello\u000D"
    val str3 = "\u000D"
    
    return if (str1[0].code == 13 && str2.last().code == 13 && str3.single().code == 13) "OK" else "NOK"
}