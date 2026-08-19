// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 13 -> sentence 13
 *                declarations, classifier-declaration, companion-object -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: companion object member reference C.Companion::zero infers type () -> Int, verifying type inference
 * HELPERS: checkType
 */

class C {
    companion object {
        fun zero(): Int = 0
    }
}

// TESTCASE NUMBER: 1
fun case1() {
    val f: () -> Int = C.Companion::zero
    checkSubtype<() -> Int>(f)
}
