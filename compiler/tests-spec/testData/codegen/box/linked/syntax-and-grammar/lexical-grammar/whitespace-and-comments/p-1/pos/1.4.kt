// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: LF character at beginning and end of string
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str1 = "\u000Ahello"
    val str2 = "hello\u000A"
    val str3 = "\u000A"
    
    return if (str1.lines().size == 2 && str2.lines().size == 2 && str3.lines().size == 2) "OK" else "NOK"
}