// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 374 -> sentence 374
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 374 -> sentence 374
 *                declarations, property-declaration -> paragraph 374 -> sentence 374
 *                declarations, classifier-declaration, companion-object -> paragraph 374 -> sentence 374
 * NUMBER: 1
 * DESCRIPTION: companion object cannot directly access outer instance private val
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int) {
    companion object {
        fun bad(): Int = <!UNRESOLVED_REFERENCE!>secret<!>
    }
}
