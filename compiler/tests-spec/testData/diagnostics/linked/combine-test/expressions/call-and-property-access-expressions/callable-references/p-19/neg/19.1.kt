// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 19 -> sentence 19
 *                overload-resolution, resolving-callable-references -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: bound reference to overloaded instance member without expected type is ambiguous, verifying compile-time failure
 */

class C {
    fun work() {}
    fun work(x: Int) {}
}

// TESTCASE NUMBER: 1
fun case1(c: C) = c::<!OVERLOAD_RESOLUTION_AMBIGUITY!>work<!>
