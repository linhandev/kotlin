// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 95 -> sentence 95
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body can access primary-initialized member after this() delegation in class declaration
 */

// TESTCASE NUMBER: 1
class Log(val msg: String) {
    var bodyObserved = ""

    constructor(code: Int) : this(code.toString()) {
        bodyObserved = msg
    }
}

fun viaSecondaryInt(): Log = Log(1)

fun viaSecondaryAnother(): Log = Log(42)

fun viaPrimary(): Log = Log("hi")

fun box(): String {
    val fromInt = viaSecondaryInt()
    if (fromInt.msg != "1") return "NOK: int msg"
    if (fromInt.bodyObserved != "1") return "NOK: int body"
    val fromAnother = viaSecondaryAnother()
    if (fromAnother.msg != "42") return "NOK: another msg"
    if (fromAnother.bodyObserved != "42") return "NOK: another body"
    val fromPrimary = viaPrimary()
    if (fromPrimary.msg != "hi") return "NOK: primary msg"
    if (fromPrimary.bodyObserved != "") return "NOK: primary body"
    return "OK"
}
