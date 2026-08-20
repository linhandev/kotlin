// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 93 -> sentence 93
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 93 -> sentence 93
 *                expressions, when-expressions -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: when with custom equals branches infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box(val x: Int) {
    override fun equals(other: Any?) = other is Box && x == other.x
    override fun hashCode() = x
}

fun case1(b: Box) {
    checkSubtype<String>(
        when (b) {
            Box(42) -> "yes"
            else -> "no"
        }
    )
}
