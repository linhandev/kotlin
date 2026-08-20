// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 24 -> sentence 24
 *                declarations, declaration-visibility -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: internal class type is invisible across modules even when calling its public members
 */
// MODULE: lib56024
// FILE: Lib.kt
package pkg56024.api

internal class Cache56024 {
    fun ping56024(): Int = 1
}

// MODULE: other56024(lib56024)
// FILE: Other.kt
package pkg56024.other

// TESTCASE NUMBER: 1
fun case_1() {
    pkg56024.api.<!INVISIBLE_MEMBER!>Cache56024<!>()
}
