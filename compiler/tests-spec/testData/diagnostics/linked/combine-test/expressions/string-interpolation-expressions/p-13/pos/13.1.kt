// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: custom toString in simple identifier interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Id(val v: Int) {
    override fun toString(): String = "#$v"
}

fun case1() {
    val id = Id(1)
    checkSubtype<String>("id=$id")
}
