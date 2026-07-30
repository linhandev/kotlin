// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: object can implement a Java functional interface
 */

// TESTCASE NUMBER: 1
object Runner : java.lang.Runnable {
    var ran = false
    override fun run() {
        ran = true
    }
}

fun test(): Boolean {
    Runner.ran = false
    Runner.run()
    return Runner.ran
}

fun box(): String {
    if (!test()) return "NOK: test"
    val r: java.lang.Runnable = Runner
    Runner.ran = false
    r.run()
    if (!Runner.ran) return "NOK: typed"
    return "OK"
}
