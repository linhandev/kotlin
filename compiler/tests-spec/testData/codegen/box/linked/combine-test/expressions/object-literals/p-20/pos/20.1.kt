
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: object literal can implement Java functional interface
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    var ran = 0
    val r = object : java.lang.Runnable {
        override fun run() {
            ran = 1
        }
    }
    r.run()
    return ran
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
