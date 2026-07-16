// DIAGNOSTICS: -UNUSED_PARAMETER -UNRESOLVED_REFERENCE -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 4 -> sentence 4
 * declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 6 -> sentence 6
 * SECONDARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 6
 * DESCRIPTION: inner class val constructor properties are readable in init block and member functions including captured outer generic type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    inner class A(val x: Any?) {
        init {
            x checkType { check<Any?>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any?")!>x<!>
        }

        fun test() {
            x checkType { check<Any?>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any?")!>x<!>
        }
    }

    inner class B(val x: Any) {
        init {
            x checkType { check<Any>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any")!>x<!>
        }

        fun test() {
            x checkType { check<Any>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Any")!>x<!>
        }
    }

    inner class C(val x: () -> Any) {
        init {
            x checkType { check<() -> Any>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Function0<kotlin.Any>")!>x<!>
        }

        fun test() {
            x checkType { check<() -> Any>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Function0<kotlin.Any>")!>x<!>
        }
    }

    inner class D(val x: Enum<*>) {
        init {
            x checkType { check<Enum<*>>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Enum<*>")!>x<!>
        }

        fun test() {
            x checkType { check<Enum<*>>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Enum<*>")!>x<!>
        }
    }

    inner class E(val x: Nothing) {
        init {
            x checkType { check<Nothing>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Nothing")!>x<!>
        }

        fun test() {
            x checkType { check<Nothing>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Nothing")!>x<!>
        }
    }

    inner class F<T>(val x: T) {
        init {
            x checkType { check<T>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("T")!>x<!>
        }

        fun test() {
            x checkType { check<T>() }
            <!DEBUG_INFO_EXPRESSION_TYPE("T")!>x<!>
        }
    }

}

// TESTCASE NUMBER: 2
class Case2<T>() {
    inner class A(val x: Any?, t: T)
    inner class B(val x: T)
    inner class C(val c: () -> T)
    inner class D<T : Enum<*>>(val e: T)
    inner class E(val n: Nothing, val t: T)
    inner class F<T>(val t: T)
}
