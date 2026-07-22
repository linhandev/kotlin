// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Code block allows optional trailing and empty semicolon-separated statements
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 0
    run {
        ;
        x = 1;
        ;
    }
    return if (x == 1) "OK" else "NOK"
}
