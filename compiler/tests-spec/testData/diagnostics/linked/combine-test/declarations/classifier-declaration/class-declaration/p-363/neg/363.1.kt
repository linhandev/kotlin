// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 363 -> sentence 363
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 363 -> sentence 363
 *                declarations, property-declaration -> paragraph 363 -> sentence 363
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 363 -> sentence 363
 * NUMBER: 1
 * DESCRIPTION: 非 inner 嵌套类不能访问外部实例 private val
 */

// TESTCASE NUMBER: 1
class Outer(private val secret: Int) {
    class Nested {
        fun get(): Int = <!UNRESOLVED_REFERENCE!>secret<!>
    }
}
