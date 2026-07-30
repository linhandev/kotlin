// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: companion object String property can use lazy delegate
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val lazyValue: String by lazy { "lazy" }
    }
}

fun test() = Box.lazyValue

fun box(): String {
    if (test() != "lazy") return "NOK"
    return "OK"
}
