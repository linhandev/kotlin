// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, inheritance-from-built-in-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function type inheritance dispatches invoke correctly at runtime
 */

// TESTCASE NUMBER: 1
class Increment513 : (Int) -> Int {
    override fun invoke(value: Int): Int = value + 10
}

fun box(): String {
    val inc: (Int) -> Int = Increment513()
    return if (inc(5) == 15) "OK" else "NOK"
}
