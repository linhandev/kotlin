// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: same-file private top-level class is usable by other declarations in that file type inference
 * HELPERS: checkType
 */

private class Secret56031(val v: Int = 8)

class Facade56031 {
    fun ok(): Int = Secret56031().v
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(Facade56031().ok())
}
