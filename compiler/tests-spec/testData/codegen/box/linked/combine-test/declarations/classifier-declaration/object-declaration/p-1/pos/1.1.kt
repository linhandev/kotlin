// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object declaration can implement a single interface
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick(): String
}

object Btn : Click {
    override fun onClick(): String = "ok"
}

fun test(): String = Btn.onClick()

fun box(): String {
    if (test() != "ok") return "NOK: test"
    val c: Click = Btn
    if (c.onClick() != "ok") return "NOK: as-click"
    return "OK"
}
