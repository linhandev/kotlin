/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 317 -> sentence 317
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 317 -> sentence 317
 * NUMBER: 1
 * DESCRIPTION: anonymous object extending an inner class must bind to an outer instance
 */

// TESTCASE NUMBER: 1
class Outer {
    inner open class Handler {
        open fun run(): Int = 1
    }

    fun makeHandler(): Handler = object : Handler() {
        override fun run(): Int = 2
    }
}

fun test(): Int = Outer().makeHandler().run()

fun box(): String {
    if (test() != 2) return "NOK: test"
    val o = Outer()
    if (o.makeHandler().run() != 2) return "NOK: direct"
    if (Outer().makeHandler() !is Outer.Handler) return "NOK: type"
    return "OK"
}
