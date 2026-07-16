// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 25 -> sentence 25
 * NUMBER: 3
 * DESCRIPTION: SUB_ASSIGNMENT token used with custom minusAssign operator
 */
// TESTCASE NUMBER: 1

class Balance(var amount: Int) {
    operator fun minusAssign(delta: Int) {
        amount -= delta
    }
}

fun box(): String {
    val balance = Balance(20)
    balance -= 8
    return if (balance.amount == 12) "OK" else "NOK"
}
