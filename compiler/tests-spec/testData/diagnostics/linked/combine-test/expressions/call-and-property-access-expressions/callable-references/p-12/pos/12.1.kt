// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: bound property reference this::v inside a class infers type () -> Int and captures the receiver instance, verifying type inference
 * HELPERS: checkType
 */

class C(val v: Int) {
    fun getF(): () -> Int = this::v
}

// TESTCASE NUMBER: 1
fun case1() {
    val f: () -> Int = C(3).getF()
    checkSubtype<() -> Int>(f)
}
