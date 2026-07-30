// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 275 -> sentence 275
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 275 -> sentence 275
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 275 -> sentence 275
 *                declarations, property-declaration -> paragraph 275 -> sentence 275
 * NUMBER: 1
 * DESCRIPTION: a non-inner nested class cannot read outer-instance private members without an outer instance (UNRESOLVED_REFERENCE); covers primary-constructor property, body property, and private function; contrasts with previous-point inner success
 */

// TESTCASE NUMBER: 1
class TokenOuter(private val secret: Int) {
    class Nested {
        fun get(): Int = <!UNRESOLVED_REFERENCE!>secret<!>
    }
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private val code: Int = 2
    class Nested {
        fun open(): Int = <!UNRESOLVED_REFERENCE!>code<!>
    }
}

// TESTCASE NUMBER: 3
class LabelOuter(private val seed: String) {
    private fun label(): String = seed
    class Nested {
        fun text(): String = <!UNRESOLVED_REFERENCE!>label<!>()
        fun seedRef(): Any = <!UNRESOLVED_REFERENCE!>seed<!>
    }
}
