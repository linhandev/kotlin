// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Boolean and/or/xor infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    var n = 0
    fun side(): Boolean { n++; return true }
    checkSubtype<Boolean>(false.or(side()))
    checkSubtype<Boolean>(false.and(side()))
    checkSubtype<Boolean>(false.xor(side()))
}
