// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: as? Number then toInt infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any? = 3
    checkSubtype<Int?>((x as? Number)?.toInt())
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any? = null
    checkSubtype<Int?>((x as? Number)?.toInt())
}
