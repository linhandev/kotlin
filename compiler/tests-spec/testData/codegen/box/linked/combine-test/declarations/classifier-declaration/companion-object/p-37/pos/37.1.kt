// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: takeUnless on companion property returns null when predicate is true
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
    }
}

fun test() = Box.x.takeUnless { it > 0 }

fun box(): String {
    if (test() != null) return "NOK"
    return "OK"
}
