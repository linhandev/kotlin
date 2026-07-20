// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 4 -> sentence 4
 * declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * SECONDARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: local class inside generic extension function, property getter, and setter may use receiver type parameter in constructor parameters
 */

// TESTCASE NUMBER: 1
fun <T> List<T>.case1() {
    class Case1(t: T)
    class A(t: T)
    class B(x: List<T>)
    class C(c: () -> T)
    class E(n: Nothing, t: T)
}

// TESTCASE NUMBER: 2
val <T> List<T>.case2: Int
    get() = {
        class A(t: T)
        class B(x: List<T>)
        class C(c: () -> T)
        class E(n: Nothing, t: T)
        1
    }()

// TESTCASE NUMBER: 3
var <T> List<T>.case3: Unit
    get() {
        class A(t: T)
        class B(x: List<T>)
        class C(c: () -> T)
        class E(n: Nothing, t: T)
        1
    }
    set(i: Unit) {
        class A(t: T)
        class B(x: List<T>)
        class C(c: () -> T)
        class E(n: Nothing, t: T)
    }

