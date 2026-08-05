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
 * DESCRIPTION: 伴生对象不能直接访问外部类实例的 private val
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int) {
    companion object {
        fun bad(): Int = <!UNRESOLVED_REFERENCE!>secret<!>
    }
}
