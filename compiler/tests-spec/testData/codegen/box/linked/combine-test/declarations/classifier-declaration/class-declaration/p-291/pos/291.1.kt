// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 291 -> sentence 291
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 291 -> sentence 291
 *                declarations, classifier-declaration, local-class-declaration -> paragraph 291 -> sentence 291
 * NUMBER: 1
 * DESCRIPTION: public members of a local class are accessible in the declaring function body; covers primary-constructor property, member function, and body property; contrasts with previous-point private local member failure
 */

// TESTCASE NUMBER: 1
fun localProp(): Int {
    class Local(val v: Int)
    return Local(1).v
}

// TESTCASE NUMBER: 2
fun localFun(): Int {
    class Local {
        fun code(): Int = 2
    }
    return Local().code()
}

// TESTCASE NUMBER: 3
fun localBody(): String {
    class Local {
        val label: String = "L"
        fun text(): String = label
    }
    return Local().text()
}

fun box(): String {
    if (localProp() != 1) return "NOK: prop"
    if (localProp() != 1) return "NOK: prop-again"

    if (localFun() != 2) return "NOK: fun"
    if (localFun() != 2) return "NOK: fun-again"

    if (localBody() != "L") return "NOK: body"
    if (localBody().length != 1) return "NOK: body-len"
    return "OK"
}
