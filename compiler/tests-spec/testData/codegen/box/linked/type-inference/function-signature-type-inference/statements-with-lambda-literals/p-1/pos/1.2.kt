/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: five-step lambda statement processing — step 5 expected Double constrains nested run at runtime
 */
// TESTCASE NUMBER: 1

fun <T> foo1432(): T {
    @Suppress("UNCHECKED_CAST")
    return 0.0 as T
}

fun <R> run1432(body: () -> R): R = body()

fun expectedDouble1432(): Double = run1432 {
    run1432 {
        foo1432()
    }
}

fun box(): String = if (expectedDouble1432() == 0.0) "OK" else "NOK"
