/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: percent uses rem overload; infix mod is explicit call not percent operator
 */

// TESTCASE NUMBER: 1
data class N(val v: Int) {
    var remCalled = false
    var modCalled = false

    operator fun rem(m: Int): N {
        remCalled = true
        return N(v % m)
    }

    infix fun mod(m: Int): N {
        modCalled = true
        return N(((v % m) + m) % m)
    }
}

fun testRem(): N {
    val n = N(7)
    val r = n % 3
    return N(r.v).also { it.remCalled = n.remCalled; it.modCalled = n.modCalled }
}

fun testMod(): N {
    val n = N(7)
    val r = n mod 3
    return N(r.v).also { it.remCalled = n.remCalled; it.modCalled = n.modCalled }
}

fun box(): String {
    val remResult = testRem()
    if (remResult.v != 1) return "NOK rem value"
    if (!remResult.remCalled) return "NOK rem not called"
    if (remResult.modCalled) return "NOK mod called via percent"

    val modResult = testMod()
    if (modResult.v != 1) return "NOK mod value"
    if (!modResult.modCalled) return "NOK mod not called"
    if (modResult.remCalled) return "NOK rem called via infix mod"
    return "OK"
}
