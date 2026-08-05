// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 43 -> sentence 43
 *                declarations, function-declaration, extension-function-declaration -> paragraph 43 -> sentence 43
 *                type-system, introduction-1 -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: extension contains on smart-cast nullable receiver with in infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val ok: Boolean)
operator fun Box.contains(x: Int): Boolean = ok

fun case1(b: Box?): Boolean = if (b != null) 1 in b else false

fun case2() {
    checkSubtype<Boolean>(case1(null))
    checkSubtype<Boolean>(case1(Box(true)))
}
