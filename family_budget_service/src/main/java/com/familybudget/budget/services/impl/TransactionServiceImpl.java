package com.familybudget.budget.services.impl;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Long budgetId, TransactionRequest request) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));

        Transaction transaction = new Transaction();
        transaction.setBudget(budget);
        transaction.setCategory(category);
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());

        return transactionRepository.save(transaction);
    }
}
