// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -EXTENSION_SHADOWED_BY_MEMBER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, function-declaration, extension-function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: in operator prefers class member contains over extension contains and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    val list = listOf(1, 2, 3)
    operator fun contains(x: Int): Boolean = x in list
}

operator fun Box.contains(x: Int): Boolean = false

fun case1() {
    checkSubtype<Boolean>(2 in Box())
}
