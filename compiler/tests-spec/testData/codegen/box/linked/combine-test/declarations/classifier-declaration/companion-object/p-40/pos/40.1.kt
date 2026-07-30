// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: synchronized in companion object returns the block result
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val lock = Any()
        fun safe() = synchronized(lock) { 42 }
    }
}

fun test() = Box.safe()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
