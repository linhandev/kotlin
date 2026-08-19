// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 20 -> sentence 20
 *                overload-resolution, resolving-callable-references -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: expected type () -> Unit selects no-arg work overload for bound callable reference, verifying type inference
 * HELPERS: checkType
 */

class C {
    fun work() {}
    fun work(x: Int) {}
}

// TESTCASE NUMBER: 1
fun case1(c: C) {
    val f: () -> Unit = c::work
    checkSubtype<() -> Unit>(f)
}
