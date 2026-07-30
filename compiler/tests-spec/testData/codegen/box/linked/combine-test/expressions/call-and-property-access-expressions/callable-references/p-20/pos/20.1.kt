// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 20 -> sentence 20
 *                overload-resolution, resolving-callable-references -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: expected type () -> Unit selects no-arg work overload for bound callable reference, verifying runtime semantics
 */

class C {
    var called = false
    fun work() { called = true }
    fun work(x: Int) { called = false }
}

// TESTCASE NUMBER: 1
fun test(c: C) {
    val f: () -> Unit = c::work
    f()
}

fun box(): String {
    val c = C()
    test(c)
    if (!c.called) return "NOK"
    return "OK"
}
