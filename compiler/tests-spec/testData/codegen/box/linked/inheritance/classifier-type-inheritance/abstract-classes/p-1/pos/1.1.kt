// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: abstract class subtype can be used polymorphically at runtime
 */

// TESTCASE NUMBER: 1
abstract class Animal511 {
    abstract fun speak(): String
}

class Dog511 : Animal511() {
    override fun speak(): String = "woof"
}

fun box(): String {
    val animal: Animal511 = Dog511()
    return if (animal.speak() == "woof") "OK" else "NOK"
}
