
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: object literal can access outer class private members
 */

// TESTCASE NUMBER: 1
class Host {
    private val secret = 1
    var captured = 0

    fun make(): Runnable = object : Runnable {
        override fun run() {
            captured = secret
        }
    }
}

fun test(): Int {
    val host = Host()
    host.make().run()
    return host.captured
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
