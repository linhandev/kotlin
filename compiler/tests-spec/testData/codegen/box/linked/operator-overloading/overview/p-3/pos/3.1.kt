// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: C[0][0]++ expands to nested get/set/inc and evaluates outer C.get(0) only once
 */

// TESTCASE NUMBER: 1
class A9031(var value: Int) {
    operator fun inc(): A9031 {
        value++
        return this
    }
}

class B9031 {
    var stored: A9031? = null
    var log = ""

    operator fun get(i: Int): A9031 {
        log += "B.get($i);"
        return stored!!
    }

    operator fun set(i: Int, value: A9031) {
        log += "B.set($i);"
        stored = value
    }
}

object C9031 {
    var b: B9031? = null
    var getCount = 0
    var log = ""

    operator fun get(i: Int): B9031 {
        getCount++
        log += "C.get($i);"
        return b!!
    }
}

fun box(): String {
    val a = A9031(0)
    val b = B9031()
    b.stored = a
    C9031.b = b
    C9031.getCount = 0
    C9031.log = ""
    b.log = ""

    C9031[0][0]++

    if (C9031.getCount != 1) return "NOK getCount: ${C9031.getCount}"
    if (a.value != 1) return "NOK value: ${a.value}"
    if (!C9031.log.startsWith("C.get(0);")) return "NOK C.log: ${C9031.log}"
    if (!b.log.contains("B.get(0);")) return "NOK B.log: ${b.log}"
    return "OK"
}
