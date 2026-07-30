// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 93 -> sentence 93
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 93 -> sentence 93
 *                expressions, when-expressions -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: when subject matches via custom equals on class member
 */

// TESTCASE NUMBER: 1

class Box(val x: Int) {
    override fun equals(other: Any?) = other is Box && x == other.x
    override fun hashCode() = x
}

fun test(b: Box): String = when (b) {
    Box(42) -> "yes"
    else -> "no"
}

fun box(): String {
    if (test(Box(42)) != "yes") return "NOK"
    if (test(Box(0)) != "no") return "NOK"
    return "OK"
}
