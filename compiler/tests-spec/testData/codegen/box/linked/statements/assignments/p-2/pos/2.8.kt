// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 8
 * DESCRIPTION: h[0] += 10 invokes plusAssign on indexed element at runtime
 */

class Value(var n: Int) {
    operator fun plusAssign(x: Int) {
        n += x
    }
}

class Holder(private val items: Array<Value>) {
    operator fun get(i: Int): Value = items[i]
    operator fun set(i: Int, v: Value) {
        items[i] = v
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val items = arrayOf(Value(2))
    val h = Holder(items)
    h[0] += 10
    return if (items[0].n == 12) "OK" else "NOK"
}
