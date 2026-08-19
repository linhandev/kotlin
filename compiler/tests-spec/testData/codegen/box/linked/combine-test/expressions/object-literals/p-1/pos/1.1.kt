
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object literal implements single interface and is used as expression
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick(): String
}

fun test(): Click = object : Click {
    override fun onClick(): String = "ok"
}

fun box(): String {
    if (test().onClick() != "ok") return "NOK"
    return "OK"
}
