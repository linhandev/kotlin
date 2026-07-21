// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: nested run blocks increment total from 0 to 15 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var total = 0
    run {
        total += 10
        run {
            total += 5
        }
    }
    return if (total == 15) "OK" else "NOK"
}
