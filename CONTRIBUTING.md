# Contributing

**Simple Monorepo** is open to contributions.

In order to keep it simple and speed up the process of contributing, we have put a few guidelines in this document.

## Google Style Guides

Make sure that you follow the rules in [Google Style Guides](https://google.github.io/styleguide)

## Immutability

- Prefer immutable objects to mutable ones.
- Do not use `@Setter` or `@Data` annotations unless it is the last resource.
- Use `@RequiredArgsConstructor` with `private final` fields.