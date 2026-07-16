// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: overridden member is dynamically dispatched at runtime
 */

// TESTCASE NUMBER: 1
open class Base540 {
    open fun value(): Int = 1
}

class Derived540 : Base540() {
    override fun value(): Int = 2
}

fun box(): String {
    val ref: Base540 = Derived540()
    return if (ref.value() == 2) "OK" else "NOK"
}
