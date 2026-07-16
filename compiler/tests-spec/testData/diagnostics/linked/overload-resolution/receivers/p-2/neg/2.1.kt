// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: DslMarker restricts implicit receiver to highest priority receiver only
 */

// TESTCASE NUMBER: 1
@DslMarker
annotation class Ann1102

@Ann1102
class A1102 {
    fun a() = 1
}

@Ann1102
class B1102 {
    fun b() = 2
}

fun case_1() {
    val a = A1102()
    val b = B1102()
    with(a) l1@{
        a.a()
        with(b) {
            <!DSL_SCOPE_VIOLATION!>a<!>()
            this@l1.a()
            b.b()
        }
    }
}
