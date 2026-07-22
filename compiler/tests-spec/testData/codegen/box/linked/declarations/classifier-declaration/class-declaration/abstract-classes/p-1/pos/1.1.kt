// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: abstract class can be subclassed and abstract members implemented at runtime
 */

// TESTCASE NUMBER: 1
abstract class Base {
    abstract fun compute(): Int
}

class Derived : Base() {
    override fun compute(): Int = 42
}

fun box(): String {
    val result: Base = Derived()
    return if (result.compute() == 42) "OK" else "NOK"
}
