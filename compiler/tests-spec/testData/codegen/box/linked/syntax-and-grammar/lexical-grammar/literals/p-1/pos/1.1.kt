// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: DecDigitNoZero as leading digit in decimal integer literal 42
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n1 = 42     
    val n2 = 7      
    val n3 = 123456  
    return if (n1.toInt() == 42 && n2.toInt() == 7 && n3.toInt() == 123456) "OK" else "NOK"
}
