// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: override dispatch and overload resolution at runtime
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun transform(x: Int): Int = x
}

class Derived : Base() {
    override fun transform(x: Int): Int = x * 2
}

fun pick(x: Int): String = "int:$x"
fun pick(x: String): String = "str:$x"

fun box(): String {
    val value: Base = Derived()
    if (value.transform(3) != 6) return "NOK override"
    if (pick(1) != "int:1" || pick("a") != "str:a") return "NOK overload"
    return "OK"
}
