/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: exception types in catch clauses must be runtime-available for correct handler selection
 */
// TESTCASE NUMBER: 1

fun catchIllegalState1512(action: () -> Nothing): String {
    return try {
        action()
    } catch (e: IllegalStateException) {
        if (e.message != "runtime-available") return "NOK: unexpected ISE message ${e.message}"
        "caught-ise"
    } catch (e: RuntimeException) {
        "caught-other"
    }
}

fun box(): String {
    if (catchIllegalState1512 { throw IllegalStateException("runtime-available") } != "caught-ise") {
        return "NOK: IllegalStateException should match ISE catch"
    }
    if (catchIllegalState1512 { throw RuntimeException("other") } != "caught-other") {
        return "NOK: RuntimeException should not match ISE catch"
    }
    return "OK"
}
